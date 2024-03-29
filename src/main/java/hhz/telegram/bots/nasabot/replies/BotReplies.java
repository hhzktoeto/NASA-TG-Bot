package hhz.telegram.bots.nasabot.replies;

import hhz.telegram.bots.nasabot.client.NasaClient;
import lombok.Getter;

@Getter
public enum BotReplies {
    INFO("""
            Дороу. Этот бот присылает картинку дня с сайта NASA
            Кнопки для использования бота доступны на интерактивной клавиатуре
            Собственно и всё, приятного пользования:)
                            
            По всем вопросам/предложениям пиши @hhzktoeto"""),
    TODAY_PICTURE(""),
    DATE_PICTURE(""),
    DATE_GUIDE("Укажи дату в формате ГГГ-ММ-ДД (прим. 1998-06-25)"),
    INVALID_MESSAGE("""
            Неверная команда (воспользуйся интерактивной клавиатурой)
                                            
            Или неверный формат даты (прим. 2024-01-01)""");

    private String message;

    BotReplies(String message) {
        this.message = message;
    }

    public void setUrl(String url, String date) {
        if (this == DATE_PICTURE) {
            this.message = NasaClient.getResponseUrl(url + "&date=" + date);
        }
        if (this == TODAY_PICTURE) {
            this.message = NasaClient.getResponseUrl(url);
        }
    }
}
