package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.business.rules.ContactRules;
import tr.com.huseyinaydin.dto.ContactDto;
import tr.com.huseyinaydin.entity.Contact;
import tr.com.huseyinaydin.mapper.ContactMapper;
import tr.com.huseyinaydin.repository.ContactRepository;
import tr.com.huseyinaydin.service.ContactService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public void sendMessage(ContactDto contactDto) {
        Contact contact = contactMapper.toEntity(contactDto);
        contact.setDate(LocalDateTime.now());
        contactRepository.save(contact);
    }

    @Override
    public List<ContactDto> getAllMessages() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto getMessageById(Integer id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        ContactRules.checkContactExists(contact);
        return contactMapper.toDto(contact);
    }
}
