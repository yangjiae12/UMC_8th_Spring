package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodLikeList){

        return foodLikeList.stream()
                .map(foodLike ->
                        MemberPrefer.builder()
                                .foodCategory(foodLike)
                                .build()
                ).collect(Collectors.toList());
    }
}
