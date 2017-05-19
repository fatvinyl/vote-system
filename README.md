# Система голосования
Приложение представляет собой систему голосования за ресторан, в котором пользователь хотел бы пообедать.
Голосование производится ежедневно до 11:00 (для тестирования имеется возможность изменить время).
Представители ресторанов заранее присылают администратору меню дня,состоящее из 3 - 5 блюд, которые он сохраняет в базу данных.
В приложении имеется регистрация/авторизация и интерфейс на основе ролей (USER, ADMIN). Администратор может создавать/редактировать/удалять пользователей, рестораны и меню, а пользователь - управлять своим профилем и голосовать за понравившийся ресторан.
Функциональность приложения реализована через UI (по AJAX) и по REST интерфейсу с базовой авторизацией.

## <a href="http://fatvinyl.herokuapp.com">Демо разрабатываемого приложения</a> 
## Стек технологий
`Spring Security, Spring MVC, Spring Security Test, Hibernate ORM, Hibernate Validator, SLF4J, Json Jackson, JSP, JSTL, 
Apache Tomcat, WebJars, DataTables plugin, PostgreSQL, JUnit, Hamcrest, jQuery, jQuery notification, Bootstrap.`

## Запуск приложения
```
	Для используемой операционной системы необходимо установить переменную окружения среды VOTESYSTEM_ROOT 
	и указать путь к корневому каталогу проекта.
	В проекте используется Postgresql 9.5.7 со следующими параметрами конфигурации:
        database: votesystem
        name: user
        password: password
        port: 5432
        
        В pgAdmin III необходимо создать новую базу votesystem и новую роль входа (имя: user, пароль password)
```
## Внешний вид приложения

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/23616043/26207038/a8d30a8a-3c10-11e7-81dc-f99a66a2da26.png">
-
<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/23616043/26207065/b5800d28-3c10-11e7-9d3c-ac588ec416ab.png">
-
<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/23616043/26207072/b9b2522a-3c10-11e7-9ef8-47fa2de44b3f.png">
-
<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/23616043/26207078/bc335422-3c10-11e7-83d3-40ded80cdccf.png">
-