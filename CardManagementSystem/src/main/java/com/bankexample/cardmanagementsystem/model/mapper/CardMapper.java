package com.bankexample.cardmanagementsystem.model.mapper;


import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.dto.response.BalanceResponse;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "pan", target = "pan", qualifiedByName = "maskPan")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "validityPeriod", target = "validityPeriod")
    @Mapping(
            target = "userFullName",
            expression = "java(card.getUser().getLastName() + \" \" + card.getUser().getFirstName() + \" \" + card.getUser().getMiddleName())"
    )
    CardResponse fromCard(Card card);

    @Named("maskPan")
    static String maskPan(String pan) {
//        return "**** **** **** " + pan.substring(pan.length() - 4);
        return pan;
    }


    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "pan", source = "card.pan")
    @Mapping(target = "amount", source = "card.amount") // текущий баланс карты
    BalanceResponse fromCardToBalanceResponse(Card card);
}
