INSERT INTO t_role (roleName)
VALUES ('Admin'), ('User');


INSERT INTO t_user (username, phone, email, password)
VALUES ('john_doe', '123456789', 'john@example.com', '$2a$10$ITM2t3ymW2xpJZKnSyvGduq6OqQgdPcXwrBCVU8vN3LXBAbQrFS.m'),
       ('jane_smith', '987654321', 'jane@example.com', '$2a$10$ITM2t3ymW2xpJZKnSyvGduq6OqQgdPcXwrBCVU8vN3LXBAbQrFS.m'),
       ('bob_jackson', '555555555', 'bob@example.com', '$2a$10$ITM2t3ymW2xpJZKnSyvGduq6OqQgdPcXwrBCVU8vN3LXBAbQrFS.m');


INSERT INTO t_user_role (userId, roleId)
VALUES (1, 1), (1, 2), (2, 2), (3, 2);


INSERT INTO t_user_profile (userId, avatar, nickname, bio)
VALUES (1, 'path/to/avatar1.jpg', 'John Doe', 'I am a web developer.'),
       (2, 'path/to/avatar2.jpg', 'Jane Smith', 'Passionate about photography.'),
       (3, 'path/to/avatar3.jpg', 'Bob Jackson', 'Travel enthusiast.');


INSERT INTO t_blog_post (title, content, author_id, updated_time)
VALUES ('First Post', 'This is the content of the first post.', 1, CURRENT_TIMESTAMP),
       ('Second Post', 'This is the content of the second post.', 2, CURRENT_TIMESTAMP),
       ('Third Post', 'This is the content of the third post.', 3, CURRENT_TIMESTAMP);


INSERT INTO t_tag (name)
VALUES ('Technology'), ('Travel'), ('Food');


INSERT INTO t_post_tag (post_id, tag_id)
VALUES (1, 1), (2, 2), (3, 1), (3, 3);


INSERT INTO t_comment (post_id, author_id, content, updated_time)
VALUES (1, 2, 'Great post!', CURRENT_TIMESTAMP), (1, 3, 'Nice article.', CURRENT_TIMESTAMP), (2, 1, 'Thanks for sharing.', CURRENT_TIMESTAMP),
       (3, 2, 'I enjoyed reading this.', CURRENT_TIMESTAMP), (3, 3, 'Good job!', CURRENT_TIMESTAMP);


INSERT INTO t_like (post_id, userId)
VALUES (1, 2), (1, 3), (2, 1), (3, 1), (3, 2);


INSERT INTO t_favorite (post_id, userId)
VALUES (1, 1), (2, 3), (3, 1), (3, 3);
