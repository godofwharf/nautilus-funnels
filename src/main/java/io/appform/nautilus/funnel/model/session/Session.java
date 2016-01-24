/*
 * Copyright 2016 Santanu Sinha <santanu.sinha@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appform.nautilus.funnel.model.session;

import com.google.common.reflect.TypeToken;
import io.appform.nautilus.funnel.model.core.TemporalTypedEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Session extends TemporalTypedEntity<Session> {
    @NotNull
    @NotEmpty
    private String tenant;

    @NotNull
    private String path;
    private String normalizedPath;
    private Map<String, Object> attributes;

    @Builder
    public Session(String id, long timestamp, String tenant, String path, String normalizedPath, Map<String, Object> attributes) {
        super(id, timestamp);
        this.tenant = tenant;
        this.path = path;
        this.normalizedPath = normalizedPath;
        this.attributes = attributes;
    }

    @Override
    protected TypeToken<Session> token() {
        return TypeToken.of(Session.class);
    }

}
