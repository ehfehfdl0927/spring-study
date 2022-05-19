package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 객체로 생성자를 만들어준다 - Lombok기능
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; //final은 생성자 주입이기때문에 적절하게 사용가능
    private final DiscountPolicy discountPolicy;




    //의존관계 생성자주입
    //@Autowired //생성자가 한개면 생락가능
    //Autowired의 기능 중 같은 타입의 빈이 2개 이상 조회될 시에 빈의 파라미터 이름으로 조회하여 등록
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
