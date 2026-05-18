# TabPac 🏷️

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Paper-aqua?style=flat-square)
![Telegram](https://img.shields.io/badge/Telegram-@mybiopac-blue?logo=telegram&style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**TabPac** — це сучасний, легкий та гнучкий у налаштуванні плагін для Minecraft серверів Paper/Spigot (1.21.x), який керує вашим табом (Tablist) та бічною панеллю (Scoreboard). Це брендована альтернатива популярним плагінам, що включає вбудовану підтримку анімацій, багатомовність та глибоку інтеграцію з LuckPerms та PlaceholderAPI.

## ✨ Особливості

*   **Динамічний Таб**: Повністю налаштовувані заголовок (header) та підвал (footer) з підтримкою кількох рядків.
*   **Бічна панель (Scoreboard)**: Стильна панель для відображення статистики гравця, інформації про сервер та багато іншого.
*   **Система анімацій**: Створюйте плавні покадрові анімації для будь-якого тексту в табі або скорборді.
*   **Розумне сортування**: Сортування гравців у табі на основі списку пріоритетів (наприклад, Admin > Moderator > Player).
*   **Підтримка AFK**: Вбудована інтеграція з EssentialsX для відображення статусу AFK у табі.
*   **Багатомовність**: Нативна підтримка **англійської**, **російської**, **української** та **білоруської** мов.
*   **Інтеграція з PlaceholderAPI**: Підтримка тисяч плейсхолдерів для динамічних даних.
*   **Розумне виявлення помилок**: Повідомляє адміністраторів, якщо відсутні необхідні розширення PAPI або є помилки в конфігурації.

## 🚀 Команди

*   `/tabpac` — Основна команда, показує інформацію про версію.
*   `/tabpac reload` — Перезавантажує конфігурацію та файли локалізації.
*   `/tabpac papi download` — Автоматично завантажує необхідні розширення PAPI (Player, Server, LuckPerms).

## 🔑 Права (Permissions)

*   `tabpac.use` — Доступ до основної команди (за замовчуванням: true).
*   `tabpac.reload` — Право на перезавантаження плагіна (за замовчуванням: op).
*   `tabpac.admin` — Отримання повідомлень про помилки PAPI або конфігу.

## 🛠️ Налаштування

Файл `config.yml` простий та функціональний:

```yaml
language: start # en, ru, uk, be

groups-priority:
  - admin
  - default

tablist:
  header:
    - "%animation:logo%"
    - "&7Ласкаво просимо, &b%player_name%&7!"
  footer:
    - "&7Наш Telegram: &b@mybiopac"

animations:
  logo:
    update-interval-ticks: 5
    frames:
      - "&b&lT&f&labPac"
      - "&f&lT&b&la&f&lbPac"
```

## 📦 Встановлення

1.  Завантажте останню версію `TabPac-1.0.0-rec.2.jar`.
2.  Помістіть файл у папку `plugins` вашого сервера.
3.  Встановіть [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) та [LuckPerms](https://luckperms.net/).
4.  Перезапустіть сервер.
5.  Виконайте `/tabpac papi download` для налаштування необхідних плейсхолдерів.

---
Розроблено з ❤️ від **PacHub228**
Telegram: [@mybiopac](https://t.me/mybiopac)


Логин(Minecraft, через Microsoft): George36Baker65@rambler.ru:n3My8Cj2M

Логин(Почта, rambler.ru): George36Baker65@rambler.ru:n3My8Cj2M5535sa

У вас действует 7 дней гарантия на данный товар.

Если у вас есть какие-то вопросы или вам необходима помощь, не спешите оставлять негативный отзыв. Наша поддержка ответит вам, как только сможет.

Не забудьте оставить отзыв: https://m.vk.com/app6326142_-180623331?ref=group_menu