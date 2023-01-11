package com.modubiz.modu.kona.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json응답시 Rootname settting 위한 wrapper Class
 * */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseWrapper {

    private CommonResponseDto response;

}
