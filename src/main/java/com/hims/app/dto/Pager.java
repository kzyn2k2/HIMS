package com.hims.app.dto;

import java.util.List;

public record Pager<T>(List<T> data, long totalItems, int totalPages) {

}
