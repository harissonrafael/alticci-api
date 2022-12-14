package com.altice.alticci.controllers;

import com.altice.alticci.services.AlticciService;
import com.altice.alticci.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

import static com.altice.alticci.utils.PathUtils.RESOURCE_ALTICCI;
import static com.altice.alticci.utils.PathUtils.RESOURCE_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(RESOURCE_ALTICCI)
@Api(tags = Constants.ALTICCI)
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class AlticciController {

    private final AlticciService alticciService;

    @GetMapping
    @ApiOperation(value = "Map in cache", notes = "Returns the map with all values already created and cached")
    public ResponseEntity<Map<Long, BigDecimal>> getAllInCache() {
        return ResponseEntity.ok(alticciService.getAllInCache());
    }

    @GetMapping(value = RESOURCE_ID, produces = "application/json")
    @ApiOperation(value = "Find by index", notes = "Returns the sequence value based on the passed number")
    public ResponseEntity<String> getByIndex(@PathVariable Long id) {
        return ResponseEntity.ok(alticciService.getValueByIndex(id).toString());
    }

}
