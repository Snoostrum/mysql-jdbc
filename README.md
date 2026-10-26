# mysql-jdbc

个人在服务器上学 MySQL 的项目，基于 Claude 自学。

## 需要什么

- **JDK 21**（`java -version` 显示 21.x 即可）
- **MySQL 8**（开发时用的是 `mysql:8.4` 容器）
- **JDBC 驱动**：`lib/mysql-connector-j-9.4.0.jar`，已在仓库里，不用另外下载
- **`db.properties`**：仓库里没有，只有模板

## 怎么跑

1. 建库建表。数据库里得先有 `learn` 库和 `note` 表，否则跑起来会报：

```
Table 'learn.note' doesn't exist
```

```sql
CREATE DATABASE IF NOT EXISTS learn DEFAULT CHARSET utf8mb4;
USE learn;

CREATE TABLE note (
  id         INT          NOT NULL AUTO_INCREMENT,
  title      VARCHAR(100) NOT NULL,
  content    TEXT,
  created_at DATETIME     DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

2. 复制配置模板，填上你的数据库密码：

   ```bash
   cp db.properties.example db.properties
   ```

3. 跑：

   ```bash
   ./run.sh
   ```

   如果看到「读取数据库配置失败…」，说明第 2 步没做，或者密码填错了。
