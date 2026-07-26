package nl.utwente.sosoc.identitymanagement.service;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.identitymanagement.entity.UserEntity;
import nl.utwente.sosoc.identitymanagement.model.User;
import nl.utwente.sosoc.identitymanagement.repository.UserRepository;
import nl.utwente.sosoc.identitymanagement.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdentityManagementService {

    @Autowired private UserRepository userRepository;
    @Autowired private EntityMapper entityMapper;

    @PostConstruct
    public void init() {
    }

}
