package com.mysticmocha.mysticmocha.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    public JwtChannelInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");
            
            if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
                System.out.println("Token ausente ou inválido");
                return null; 
            }

            token = token.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);
                if (!jwtUtil.isTokenValid(token, username)) {
                    System.out.println("Token expirado ou inválido");
                    return null; 
                }
                accessor.setUser(() -> username);
            } catch (Exception e) {
                System.out.println("Erro ao validar token: " + e.getMessage());
                return null; 
            }
        }

        return message;
    }

}
