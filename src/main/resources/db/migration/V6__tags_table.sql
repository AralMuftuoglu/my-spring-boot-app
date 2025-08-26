CREATE table tags(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null
);

CREATE TABLE user_tags(
    user_id BIGINT NOT NULL ,
    tag_id BIGINT NOT NULL ,
    PRIMARY KEY (user_id,tag_id),
    FOREIGN KEY (user_id)references users(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id)references tags(id) ON DELETE CASCADE

);