package model.entity;

import view.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Projectile extends Entity {

    private String direction;
    private int speed = 5; // Tốc độ của viên đạn lửa

    public Projectile(GamePanel gp, int x, int y, String direction) {
        super(gp);

        this.worldX = x;
        this.worldY = y;
        this.direction = direction;

        // Tải hình ảnh viên đạn lửa
        setupFireballImage();

        // Khởi tạo solidArea
        solidArea = new Rectangle(0, 0, 16, 16); // Kích thước của viên đạn lửa
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    private void setupFireballImage() {
        // Tải hình ảnh cho viên đạn lửa
        image = setup("/projectiles/fireball");
    }

    @Override
    public void update() {
        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

        // Kiểm tra va chạm với các đối tượng khác
        gp.collisionChecker.CheckTile(this);
        if (collidisionOn) {
            // Nếu viên đạn lửa va chạm với vật thể, xử lý va chạm
            gp.removeProjectile(this);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, worldX, worldY, gp.titleSize / 2, gp.titleSize / 2, null);
    }
}
