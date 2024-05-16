package model.entity;

import view.GamePanel;

public class Monster extends Entity {

    private long lastFireTime;
    private long fireInterval = 3000; // Thời gian giữa các lần phun lửa (ms)

    public Monster(GamePanel gp) {
        super(gp);

        name = "Monster";
        collision = true;

        down[1] = setup("/monster/monster00");
        left[1] = setup("/monster/monster01");
        up[1] = setup("/monster/monster02");
        right[1] = setup("/monster/monster03");

        // Initialize last fire time
        lastFireTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        super.update();
        fire();
    }

    private void fire() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFireTime >= fireInterval) {
            // Thực hiện hành động phun lửa
            createFireball();

            // Cập nhật thời gian phun lửa lần cuối
            lastFireTime = currentTime;
        }
    }

    private void createFireball() {
        // Tạo đối tượng fireball và thêm vào game
        // Vị trí xuất hiện dựa vào vị trí của Monster và hướng hiện tại
        int fireballX = worldX;
        int fireballY = worldY;
        String fireballDirection = direction;

        Projectile fireball = new Projectile(gp, fireballX, fireballY, fireballDirection);
        gp.addProjectile(fireball);
    }
}
