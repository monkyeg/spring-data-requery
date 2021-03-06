/*
 * Copyright 2018 Coupang Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.requery.kotlin.converters

import io.requery.Converter
import java.time.LocalTime

/**
 * org.springframework.data.requery.kotlin.converters.LocalTimeToLongConverter
 * @author debop
 * @since 18. 7. 2
 */
class LocalTimeToLongConverter : Converter<LocalTime, Long> {

    override fun getPersistedType(): Class<Long> = Long::class.java
    override fun getMappedType(): Class<LocalTime> = LocalTime::class.java
    override fun getPersistedSize(): Int? = null

    override fun convertToMapped(type: Class<out LocalTime>?, value: Long?): LocalTime? {
        return value?.let { LocalTime.ofNanoOfDay(it) }
    }

    override fun convertToPersisted(value: LocalTime?): Long? {
        return value?.toNanoOfDay()
    }


}