Варіант №21
Система Ресторан. Клієнт робить замовлення з меню.
Адміністратор підтверджує замовлення і відправляє його на кухню для виконання.
Адміністратор виставляє рахунок. Клієнт робить його оплату

1. База даних  - MySql
2. Версія Java 8
3. Maven

Як встановити:

Clone project
Запустити schema.sql що знаходиться в папці resources/ 
Запустити populate.sql що знаходиться в папці resources/ 
Обновити логін і пароль в файлі resources/dbsettings.properties
Run in terminal command a. mvn clean tomcat7:run b. ? Add configuration / command
Перейти за посиланням localhost:8080/

Business logic:

User could login as lecturer or student. - LoginCommand
Student could register on site. - RegisterCommand
All could see courses page. - CoursesCommand (GET)
All could see specific course page. - CoursesCommand (GET)
Student could apply to course. - CoursesCommand (POST)
Lecturer could add ratings - LecturerRatingCommand (POST)
Lecturer could see all ratings for his courses - LecturerRatingCommand (GET)
Admin could add Lecturer user - AdminCommand
Admin could add Course - AdminCommand
Admin could see Ratings - AdminRatingCommand
Admin could change Ratings - AdminRatingCommand
Student could see Ratings - StudentRatingCommand
User on site could change language - LanguageCommand
Admin could send notifications - NotificationCommand
User could logout