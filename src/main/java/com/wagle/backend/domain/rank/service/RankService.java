package com.wagle.backend.domain.rank.service;

import com.wagle.backend.domain.course.domain.Course;
import com.wagle.backend.domain.course.service.CourseDomainService;
import com.wagle.backend.domain.member.domain.Member;
import com.wagle.backend.domain.rank.dto.RankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {

    private final CourseDomainService courseDomainService;

    public List<RankResponseDto> allCourseRankSearch() {
        List<Course> courseAll = courseDomainService.getCourseAll();
        return this.getCourseRankSearch(courseAll);
    }

    public List<RankResponseDto> myCourseRankSearch(Member member) {
        List<Course> courseAll = courseDomainService.getCourseByMemberId(member.getId());
        return this.getCourseRankSearch(courseAll);
    }

    private List<RankResponseDto> getCourseRankSearch(List<Course> list) {
        List<RankResponseDto> result = new ArrayList<>();

        for(Course course : list) {
            RankResponseDto dto = RankResponseDto.builder()
                    .courseName(course.getName())
                    .likeCount(courseDomainService.getCourseLikeCount(course.getId()))
                    .build();
            result.add(dto);
        }

        result.sort((o1, o2) -> o2.getLikeCount() - o1.getLikeCount());

        int count = 1;
        for(RankResponseDto dto : result) {
            if(count > 6) break;
            dto.setRank(count);
            count++;
        }

        final int N = 5;
        if (result.size() > N) {
            result.subList(N, result.size()).clear();
        }
        return result;
    }
}
