-- Prosody Configuration for Jitsi

-- Server Settings
admins = { "focus@sh1bari.ru" }

-- SSL configuration (если вы используете SSL через Nginx)
https_ports = { 5281 }
ssl = {
    key = "/etc/letsencrypt/live/sh1bari.ru/privkey.pem";  -- Путь к вашему приватному ключу
    certificate = "/etc/letsencrypt/live/sh1bari.ru/fullchain.pem";  -- Путь к вашему сертификату
}

-- Jitsi Meet Hostname Configuration
VirtualHost "sh1bari.ru"
    -- Подключение к другим сервисам
    authentication = "anonymous"
    c2s_require_encryption = true

    -- MUC для конференций
    modules_enabled = {
        "muc_domain_mapper";
    }

    muc_room_cache_size = 1000
    muc_max_users = 500
    -- Logging
    log_level = 3

-- Модуль для записи видео (если используется)
-- module_enabled = { "callstats" }

-- Настройка STUN/TURN серверов
stun = "stun:sh1bari.ru:3478"   -- Указываем ваш STUN сервер

-- Настройка TURN сервера (если используете TURN)
turncredentials = {
    { username = "turnuser", credential = "turnpassword", host = "turn:sh1bari.ru", port = "3478" };
}

-- Настройки для Jitsi Videobridge
Component "jitsi-videobridge.sh1bari.ru"
    component_secret = "secret"  -- Секрет для видеомоста
    muc_room_cache_size = 1000
    muc_max_users = 500

-- Для работы Jicofo и Videobridge через Prosody
Component "focus.sh1bari.ru"
    component_secret = "focus-secret"
    modules_enabled = { "conference_duration" }
