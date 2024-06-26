package com.portfolio.simpleboard.service.member.search;


import com.portfolio.simpleboard.dto.member.MemberProfileDTO;
import com.portfolio.simpleboard.dto.pager.PageRequestDTO;
import com.portfolio.simpleboard.dto.pager.PageResponseDTO;
import com.portfolio.simpleboard.entity.MemberProfile;
import com.portfolio.simpleboard.entity.QMemberProfile;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;


@Log4j2
public class MemberProfileSearchImpl extends QuerydslRepositorySupport implements MemberProfileSearch{

    MemberProfileSearchImpl() {
        super(MemberProfile.class);
    }

    public PageResponseDTO<MemberProfileDTO> searchMemberProfile(PageRequestDTO pageRequestDTO) {


        QMemberProfile memberProfile = QMemberProfile.memberProfile;
        JPQLQuery<MemberProfile> query = from(memberProfile);


        if(pageRequestDTO.getType() != null && pageRequestDTO.getType().length() > 0) {
            String keyword = pageRequestDTO.getKeyword();
            String[] types = pageRequestDTO.getTypes();
            if(keyword != null && keyword.length() > 0) {
                BooleanBuilder bb = new BooleanBuilder();
                for(String type : types) {
                    switch (type) {
                        case "i":
                        {
                            try{
                                Long val = Long.parseLong(keyword);
                                bb.or(memberProfile.id.eq(val));
                            } catch(NumberFormatException e) {
                                throw e;
                            }
                            break;
                        }
                        case "n":
                        {
                            bb.or(memberProfile.nickname.contains(keyword));
                            break;
                        }
                    }
                }

                query.where(bb);
            }
        }
        query.where(memberProfile.id.gt(0L));
        getQuerydsl().applyPagination(pageRequestDTO.getPageable(), query);

        int total = 0;
        var entities = query.fetch();

        var dtoList = entities.stream().map(entity->{
            return MemberProfileDTO.fromEntity(entity);
        }).toList();

        total = (int)query.fetchCount();

        return PageResponseDTO.<MemberProfileDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }
}
