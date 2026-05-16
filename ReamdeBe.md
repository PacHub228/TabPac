# TabPac 🏷️

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&style=flat-square)
![Platform](https://img.shields.io/badge/Platform-Paper-aqua?style=flat-square)
![Telegram](https://img.shields.io/badge/Telegram-@mybiopac-blue?logo=telegram&style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**TabPac** — гэта сучасны, лёгкі і гнутка наладжвальны плагін для Minecraft сервераў Paper/Spigot (1.21.x), які кіруе вашым табам (Tablist) і бакавой панэллю (Scoreboard). Гэта брэндаваная альтэрнатыва папулярным плагінам, якая ўключае ўбудаваную падтрымку анімацый, мультымоўнасць і глыбокую інтэграцыю з LuckPerms і PlaceholderAPI.

## ✨ Асаблівасці

*   **Дынамічны Таб**: Поўнасцю наладжвальны загаловак (header) і падвал (footer) з падтрымкай некалькіх радкоў.
*   **Бакавая панэль (Scoreboard)**: Стыльная панэль для адлюстравання статыстыкі гульца, інфармацыі аб серверы і шмат чаго іншага.
*   **Сістэма анімацый**: Стварайце плаўныя пакадравыя анімацыі для любога тэксту ў табе або скорбордзе.
*   **Разумная сартаванне**: Сартаванне гульцоў у табе на аснове спісу прыярытэтаў (напрыклад, Admin > Moderator > Player).
*   **Падтрымка AFK**: Убудаваная інтэграцыя з EssentialsX для адлюстравання статусу AFK у табе.
*   **Мультымоўнасць**: Натыўная падтрымка **англійскай**, **рускай**, **украінскай** і **беларускай** моў.
*   **Інтэграцыя з PlaceholderAPI**: Падтрымка тысяч плэйсхолдэраў для дынамічных даных.
*   **Разумнае выяўленне памылак**: Паведамляе адміністратарам, калі адсутнічаюць неабходныя пашырэнні PAPI або ёсць памылкі ў канфігурацыі.

## 🚀 Каманды

*   `/tabpac` — Асноўная каманда, паказвае інфармацыю аб версіі.
*   `/tabpac reload` — Перазагружае канфігурацыю і файлы лакалізацыі.
*   `/tabpac papi download` — Аўтаматычна спампоўвае неабходныя пашырэнні PAPI (Player, Server, LuckPerms).

## 🔑 Правы (Permissions)

*   `tabpac.use` — Доступ да асноўнай каманды (па змаўчанні: true).
*   `tabpac.reload` — Права на перазагрузку плагіна (па змаўчанні: op).
*   `tabpac.admin` — Атрыманне паведамленняў аб памылках PAPI або канфігу.

## 🛠️ Налада

Файл `config.yml` просты і функцыянальны:

```yaml
language: be # en, ru, uk, be

groups-priority:
  - admin
  - default

tablist:
  header:
    - "%animation:logo%"
    - "&7Сардэчна запрашаем, &b%player_name%&7!"
  footer:
    - "&7Наш Telegram: &b@mybiopac"

animations:
  logo:
    update-interval-ticks: 5
    frames:
      - "&b&lT&f&labPac"
      - "&f&lT&b&la&f&lbPac"
```

## 📦 Усталёўка

1.  Спампуйце апошнюю версію `TabPac.jar`.
2.  Змесціце файл у папку `plugins` вашага сервера.
3.  Усталюйце [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) і [LuckPerms](https://luckperms.net/).
4.  Перазапусціце сервер.
5.  Выканайце `/tabpac papi download` для налады неабходных плэйсхолдэраў.

---
Распрацавана з ❤️ ад **PacHub228**
Telegram: [@mybiopac](https://t.me/mybiopac)
