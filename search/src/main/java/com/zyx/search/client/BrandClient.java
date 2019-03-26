package com.zyx.search.client;

import com.zyx.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("item")
public interface BrandClient extends BrandApi {
}
