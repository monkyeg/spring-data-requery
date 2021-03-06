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

package org.springframework.data.requery.domain;

import io.requery.Persistable;
import io.requery.Superclass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract Entity class
 *
 * @author debop
 * @since 18. 6. 4
 */
@Superclass
public abstract class AbstractPersistable<ID> extends AbstractValueObject implements Persistable, Serializable {

    @Nullable
    abstract public ID getId();

    @io.requery.Transient
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other == null)
            return false;

        if (other instanceof AbstractPersistable) {
            AbstractPersistable that = (AbstractPersistable) other;
            return (isNew() && that.isNew())
                   ? hashCode() == other.hashCode()
                   : Objects.equals(getId(), that.getId());

        }

        return false;
    }

    @Override
    public int hashCode() {
        return (getId() != null) ? getId().hashCode() : System.identityHashCode(this);
    }

    @NotNull
    @Override
    @io.requery.Transient
    protected ToStringBuilder buildStringHelper() {
        return super.buildStringHelper()
            .add("id", getId());
    }

    private static final long serialVersionUID = 5460350519810267858L;
}
