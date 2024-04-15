package co.istad.elearning.features.instuctor.dto;

import co.istad.elearning.features.user.dto.UserCreateRequest;

public record CombinedRequest(UserCreateRequest userCreateRequest, InstructorCreateRequest instructorCreateRequest) {}
