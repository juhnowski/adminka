Администрирование
-----------------
1. Две роли
Таблица UserRoles
- id
- name
{
  1: "Администратор"
  2: "Дизайнер"  
}

2. Добавить/редактировать/удалить пользователей

Таблица Users
- id
- login
- password
- partners_id
- user_roles_id

3. Добавить/редактировать/удалить партнера

 Таблица Partners
- id
- name
{
  1: "Megogo"
  2: "Prosens"
}   

4. Устройства и расширение экрана
4.1 Добавить/редактировать/удалить запись в справочник поддерживаемых устройств - TODO определить что приходит в запросе из dashboard - наименование устройства,  версия OS, разрешение экрана

Таблица Devices
- name
- os
- screen_id

4.2 Добавить/редактировать/удалить разрешения экрана (только для Portrait, для Landscape - получаем заменой x на y). Данные по разрешениям приведены в БА

Таблица Screen
- id
- x
- y

5. Добавить/редактировать/удалить Дизайн дашборда

Таблица Dashboard
- id
- name
- default_dashboard_supported_screen_id
- template_html_id

Таблица DashboardSupportedScreens
- id
- dashboard_id
- screen_id

Таблица DashboardPosition
- id
- dashboard_id
- position_id
- afisha_type_id
- max_afisha_count

Таблица DashboardTemplateHtml
- id
- name
- html
- description

UI
--
1. UseCases
1.1 Авторизация -> выбор операции (добавить/редактировать/удалить афишу) -> выбор афиши для операций редактировать/удалить или выбор устройства для операции добавить -> загрузка афиши -> проверка на минимальные требования качества -> ввод текста -> ввод id контента -> выбор позиции в дашборде -> выбор порядка в последвательности позиции -> предпросмотр -> подтверждение результата

1.2 Push нотификация об изменении контента

1.3 Загрузка афиши
1.3.1 Афиша может быть либо прямоугольная, либо квадратная - TODO: размер надо уточнить у Марины
1.3.2 Тип афиши:
Таблица AfishaType
- id
- name
- width
- height
- title_id
[
  {
    id: 1
    name: квадратная
    width: 100
    height: 100
  }
  {
    id: 1
    name: прямоугольная
    width: 300
    height: 100
  }
]
Значения x и y для превью

1.3.2.2 Тип надписи

Таблица Title
- id
- name
- max_length
- css_name

1.3.2 При загрузки картинки анализируем размер и определяем тип картинки
1.3.3 Если афиша лежит в интервале размеров - TODO определить, то приводим к стандартным пропорциям - обрезаем длинную сторону с двух сторон
1.3.4 Интервал размеров:
- dX = -80..80
- dY = -120..120
1.3.5 Автогенерация
Генерируем файлы картинок афиш и складываем в паблик каталог статического контента, имена файлов генерируются по правилу <хэшкод текущего дата_врямя>_<x>_<y>.jpg
1.3.4 Автоудаление
Если на любом из этапов создания пользователь отказывается от дальнейшего создания афиши - автосгенерированные файлы удаляются.
1.3.5 После загрузки афиша должна быть показана на экране, что будет подтверждением того, что файл загружен и доступен
1.3.6 При вводе надписи афиши - должен отображаться счетчик оставшихся символов. Если счетчик равен нулю - дальнейший ввод символов невозможен.
1.3.7 TODO: Надо получить шрифт билайн. Все надписи только одним этим шрифтом, все параметры шрифта храним в css
1.3.8 Для переходов афиши используется не URL а id, обрабатывваемое мобильным приложением. TODO: понять откуда берется id для TV каналов, для Megogo id можно брать из url, для Prosens - пока непонятно.
1.3.9 При подтверждении результата создается/редактируется/удаляеся записи в таблице:

Таблица Afisha
- id
- name
- dashboard_position_id
- img_file_name
- title
- usrs_id
- created_dt
- edited_dt

1.4 Таблица стилей может редактироваться, файл содержит стили для всех дашбордов и располагается в каталоге public статического контента
