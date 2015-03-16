/* ========================================================================
 * Copyright 2014 SportGroup
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 SportGroup
  
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
  
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
  
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 * ========================================================================
  
  
 Source generated by CrudMaker version 1.0.0.201411201032*/
package stamp.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.enterprise.inject.Default;

import stamp.logic.api.IStampLogic;
import stamp.logic.dto.StampDTO;
import stamp.logic.dto.StampPageDTO;
import stamp.logic.converter.StampConverter;
import stamp.logic.entity.StampEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Default
@Stateless
@LocalBean
public class StampLogic implements IStampLogic {

    @PersistenceContext(unitName = "StampClassPU")
    protected EntityManager entityManager;

    public StampDTO createStamp(StampDTO sport) {
        StampEntity entity = StampConverter.persistenceDTO2Entity(sport);
        entityManager.persist(entity);
        return StampConverter.entity2PersistenceDTO(entity);
    }

    public List<StampDTO> getStamps() {
        Query q = entityManager.createQuery("select u from SportEntity u");
        return StampConverter.entity2PersistenceDTOList(q.getResultList());
    }

    public StampPageDTO getStamps(Integer page, Integer maxRecords) {
        Query count = entityManager.createQuery("select count(u) from SportEntity u");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
        Query q = entityManager.createQuery("select u from SportEntity u");
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        StampPageDTO response = new StampPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(StampConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
    }

    public StampDTO getStamp(Long id) {
        return StampConverter.entity2PersistenceDTO(entityManager.find(StampEntity.class, id));
    }

    public void deleteStamp(Long id) {
        StampEntity entity = entityManager.find(StampEntity.class, id);
        entityManager.remove(entity);
    }

    public void updateStamp(StampDTO stamp) {
        StampEntity entity = entityManager.merge(StampConverter.persistenceDTO2Entity(stamp));
        StampConverter.entity2PersistenceDTO(entity);
    }
}
