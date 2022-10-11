package com.altice.alticci.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlticciService {

    private final Map<Long, BigDecimal> mapAlticciValues;

    public Map<Long, BigDecimal> getAllInCache() {
        return mapAlticciValues;
    }

    public BigDecimal getValueByIndex(Long id) {

        if (mapAlticciValues.containsKey(id)) {
            return mapAlticciValues.get(id);
        }

        if (id == 0) {
            mapAlticciValues.putIfAbsent(0L, BigDecimal.ZERO);
        } else if (id <= 2) {
            mapAlticciValues.putIfAbsent(id, BigDecimal.ONE);
        } else {
            populateValuesByIndex(id);
        }

        return mapAlticciValues.get(id);
    }

    private void populateValuesByIndex(Long id) {
        Long i = 3L;
        while (i <= id) {
            BigDecimal first = getValueByIndex(i - 3);
            BigDecimal second = getValueByIndex(i - 2);
            BigDecimal value = first.add(second);
            mapAlticciValues.putIfAbsent(i, value);
            i++;
        }
    }

}
