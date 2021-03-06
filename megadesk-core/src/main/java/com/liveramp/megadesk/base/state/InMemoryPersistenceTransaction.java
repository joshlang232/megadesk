/**
 *  Copyright 2014 LiveRamp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liveramp.megadesk.base.state;

import java.util.Map;

import com.google.common.collect.Maps;

import com.liveramp.megadesk.core.state.Persistence;
import com.liveramp.megadesk.core.state.PersistenceTransaction;

public class InMemoryPersistenceTransaction implements PersistenceTransaction {

  private final Map<Persistence, Object> writes;

  public InMemoryPersistenceTransaction() {
    writes = Maps.newHashMap();
  }

  public <VALUE> void write(Persistence persistence, VALUE value) {
    writes.put(persistence, value);
  }

  @Override
  public void commit() {
    for (Map.Entry<Persistence, Object> entry : writes.entrySet()) {
      Persistence persistence = entry.getKey();
      Object value = entry.getValue();
      persistence.write(value);
    }
  }
}
