CREATE TABLE IF NOT EXISTS `text_files` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(100) NOT NULL,
     `password` VARCHAR(100) NOT NULL,
     `file` TEXT NOT NULL,
     `user_id` INT NOT NULL,
     PRIMARY KEY (`id`, `user_id`)
);

CREATE TABLE IF NOT EXISTS `users` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `name` VARCHAR(100) NOT NULL,
      `password` VARCHAR(100) NOT NULL,
      PRIMARY KEY (`id`)
);

INSERT INTO users (id, name, password) VALUES (1, 'root', '$2a$10$/xLnxOjby7M/ZVBf7ojmvOqhuVx3kvRSbym8d0P8mBlc1dx8vgLE6');
INSERT INTO users (id, name, password) VALUES (2, 'user', '$2a$10$C9e0To/gWZa08X9tXvBkOuQjmg648PWRnnb/DRkkG76GbsUeJBjk6');