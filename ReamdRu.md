# TabPac 🏷️

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Paper-aqua?style=flat-square)
![Telegram](https://img.shields.io/badge/Telegram-@mybiopac-blue?logo=telegram&style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**TabPac** — это современный, легкий и гибко настраиваемый плагин для Minecraft серверов Paper/Spigot (1.21.x), который управляет вашим табом (Tablist) и боковой панелью (Scoreboard). Это брендированная альтернатива популярным плагинам, включающая встроенную поддержку анимаций, мультиязычность и глубокую интеграцию с LuckPerms и PlaceholderAPI.

## ✨ Особенности

*   **Динамический Таб**: Полностью настраиваемый заголовок (header) и подвал (footer) с поддержкой нескольких строк.
*   **Боковая панель (Scoreboard)**: Стильная панель для отображения статистики игрока, информации о сервере и многого другого.
*   **Система анимаций**: Создавайте плавные покадровые анимации для любого текста в табе или скорборде.
*   **Умная сортировка**: Сортировка игроков в табе на основе настраиваемого списка приоритетов (например, Admin > Moderator > Player).
*   **Поддержка AFK**: Встроенная интеграция с EssentialsX для отображения статуса AFK в табе.
*   **Мультиязычность**: Нативная поддержка **английского**, **русского**, **украинского** и **белорусского** языков.
*   **Интеграция с PlaceholderAPI**: Поддержка тысяч плейсхолдеров для динамических данных.
*   **Умное обнаружение ошибок**: Уведомляет администраторов, если отсутствуют необходимые расширения PAPI или есть ошибки в конфигурации.

## 🚀 Команды

*   `/tabpac` — Основная команда, показывает информацию о версии.
*   `/tabpac reload` — Перезагружает конфигурацию и файлы локализации.
*   `/tabpac papi download` — Автоматически скачивает необходимые расширения PAPI (Player, Server, LuckPerms).

## 🔑 Права (Permissions)

*   `tabpac.use` — Доступ к основной команде (по умолчанию: true).
*   `tabpac.reload` — Право на перезагрузку плагина (по умолчанию: op).
*   `tabpac.admin` — Получение уведомлений об ошибках PAPI или конфига.

## 🛠️ Настройка

Файл `config.yml` прост и функционален:

```yaml
language: ru # en, ru, uk, be

groups-priority:
  - admin
  - default

tablist:
  header:
    - "%animation:logo%"
    - "&7Добро пожаловать, &b%player_name%&7!"
  footer:
    - "&7Наш Telegram: &b@mybiopac"

animations:
  logo:
    update-interval-ticks: 5
    frames:
      - "&b&lT&f&labPac"
      - "&f&lT&b&la&f&lbPac"
```

## 📦 Установка

1.  Скачайте последнюю версию `TabPac.jar`.
2.  Поместите файл в папку `plugins` вашего сервера.
3.  Установите [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) и [LuckPerms](https://luckperms.net/).
4.  Перезапустите сервер.
5.  Выполните `/tabpac papi download` для настройки необходимых плейсхолдеров.

---
Разработано с ❤️ от **PacHub228**
Telegram: [@mybiopac](https://t.me/mybiopac)
