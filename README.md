# ITStudio
IT学研会网站核心代码
✨ 功能模块
🏠 主页

粒子网络背景，支持鼠标引力 / 斥力切换
Bento Grid 数据面板（成员数、GitHub Stars、活动信息）
历届成员无限滚动展示（对接数据库）
技术栈跑马灯展示
深色 / 浅色主题切换（记忆用户偏好）

🤖 AI 问答

接入 DeepSeek API，流式输出（打字机效果）
Markdown 实时渲染
社团专属 System Prompt（了解社团背景）
快捷提问按钮

🏆 电竞排行榜

支持四款游戏：无畏契约 / 永劫无间 / APEX / 英雄联盟
段位数据存储于 MySQL，实时排序展示
上传战绩截图（模拟 AI 识别），写入数据库持久化
更新时间显示（"X hours ago"）

📋 活动报名

从后端动态加载最新活动
报名信息提交并存储到数据库
重复报名检测


club-backend/
├── src/main/java/com/itstudio/club_backend/
│   ├── controller/
│   │   ├── ActivityController.java     # 活动相关接口
│   │   ├── AiController.java           # AI 问答接口（SSE 流式）
│   │   ├── MemberController.java       # 成员展示接口
│   │   ├── RankController.java         # 电竞排行接口
│   │   └── RegisterController.java     # 活动报名接口
│   ├── service/
│   │   ├── AiService.java              # DeepSeek 流式调用
│   │   ├── MemberService.java
│   │   ├── RankService.java
│   │   └── ActivityService.java
│   ├── entity/
│   │   ├── Member.java
│   │   ├── RankRecord.java
│   │   ├── Activity.java
│   │   └── Registration.java
│   ├── repository/                     # Spring Data JPA
│   ├── dto/
│   │   ├── ApiResponse.java            # 统一响应格式
│   │   ├── AiRequest.java
│   │   └── RankUpdateRequest.java
│   └── ClubBackendApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml

frontend/
└── index.html                          # 单文件前端（Tailwind CDN）
