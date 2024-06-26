package hhz.telegram.bots.nasabot.bot;

import hhz.telegram.bots.nasabot.messages.BotReplies;
import hhz.telegram.bots.nasabot.messages.UserMessages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Value("${bot.api.url}")
    private String url;

    public SendMessage sendMessage(Update update) {
        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        if (UserMessages.INFO.getMessage().equals(message) || UserMessages.START.getMessage().equals(message)) {
            return createMessage(BotReplies.INFO.getMessage(), chatId);
        }
        if (UserMessages.TODAY_PICTURE.getMessage().equals(message)) {
            BotReplies.TODAY_PICTURE.setUrl(url, "");
            return createMessage(BotReplies.TODAY_PICTURE.getMessage(), chatId);
        }
        if (UserMessages.DATE_GUIDE.getMessage().equals(message)) {
            return createMessage(BotReplies.DATE_GUIDE.getMessage(), chatId);
        }
        if (message.matches("\\d{4}-\\d{2}-\\d{2}")) {
            BotReplies.DATE_PICTURE.setUrl(url, message);
            return createMessage(String.format(BotReplies.DATE_PICTURE.getMessage(), message), chatId);
        }
        return createMessage(BotReplies.INVALID_MESSAGE.getMessage(), chatId);
    }

    private SendMessage createMessage(String message, long chatId) {
        SendMessage sendMessageObject = SendMessage.builder()
                .chatId(chatId)
                .text(message)
                .build();
        ReplyKeyboardMarkup keyboardMarkup = ReplyKeyboardMarkup.builder()
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .keyboard(createKeyboard())
                .build();
        sendMessageObject.setReplyMarkup(keyboardMarkup);

        return sendMessageObject;
    }

    private List<KeyboardRow> createKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(UserMessages.INFO.getMessage());
        row2.add(UserMessages.TODAY_PICTURE.getMessage());
        row3.add(UserMessages.DATE_GUIDE.getMessage());

        return new ArrayList<>(List.of(row1, row2, row3));
    }
}
