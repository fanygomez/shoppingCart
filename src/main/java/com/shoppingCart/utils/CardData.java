package com.shoppingCart.utils;

import com.shoppingCart.dto.OrderDTO;
import com.shoppingCart.dto.OrderReqDTO;
import com.shoppingCart.exceptions.ErrorException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardData {
    private String fullName;
    private String account;
    private String securityCode;
    private String cardType;

    public CardData(String fullName, String account, String securityCode) {
        this.fullName = fullName;
        this.account = account;
        this.securityCode = securityCode;
    }

    public static  List<CardData> getCardData() {
        List<CardData> data = new ArrayList<>();
        CardData cardData = new CardData();
        cardData.setFullName("Stefhani Gomez");
        cardData.setAccount("4970300030000007");
        cardData.setCardType("069");
        cardData.setCardType("VISA");
        data.add(cardData);

        CardData cardData2 = new CardData();
        cardData2.setFullName("Maryory Lopez");
        cardData2.setAccount("4970300030000006");
        cardData2.setCardType("054");
        cardData2.setCardType("VISA");
        data.add(cardData);
        return data;
    }

    public static  Boolean findCard(CardData card) {
        System.out.println(getCardData().contains(card));
        if (getCardData().contains(card)){
            throw new ErrorException("Card does not exist.");
        }
        return  true;
    }
}
