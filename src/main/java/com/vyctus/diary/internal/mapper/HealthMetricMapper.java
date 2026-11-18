package com.vyctus.diary.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vyctus.diary.api.model.HealthMetricDTO;
import com.vyctus.diary.internal.entity.HealthMetric;

@Mapper(componentModel = "spring")
public interface HealthMetricMapper {

	@Mapping(target = "created", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	HealthMetric toEntity(HealthMetricDTO dto);

	HealthMetricDTO toDto(HealthMetric entity);
}