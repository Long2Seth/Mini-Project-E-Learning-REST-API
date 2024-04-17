package co.istad.elearning.features.student.dto;

import co.istad.elearning.features.user.dto.UserCreateRequest;

public record CombinedRequest(UserCreateRequest userCreateRequest, StudentCreateRequest studentCreateRequest) {}
