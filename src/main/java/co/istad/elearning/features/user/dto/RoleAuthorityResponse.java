package co.istad.elearning.features.user.dto;

import co.istad.elearning.domain.Authority;

import java.util.List;

public record RoleAuthorityResponse(
        String name,
        List<Authority> authorities
) {
}
