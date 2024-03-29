package hhz.telegram.bots.nasabot.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserMessages {
    INFO("Инфо"),
    START("/start"),
    TODAY_PICTURE("Снимок сегодняшнего дня"),
    DATE_GUIDE("Снимок за определённую дату");

    private final String message;
}
