package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.ContactDto;
import java.util.List;

public interface ContactService {
    void sendMessage(ContactDto contactDto);
    List<ContactDto> getAllMessages();
    ContactDto getMessageById(Integer id);
}
