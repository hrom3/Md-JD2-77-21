package by.bsuir;

import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;
import by.bsuir.repository.IDealerRepository;
import by.bsuir.repository.IUserRepository;
import by.bsuir.repository.impl.DealerRepositoryImpll;
import by.bsuir.repository.impl.UserRepository;

import java.sql.Date;
import java.util.stream.Collectors;

//Ctrl+W - word checking
//Alt+Shift - multiple cursor
//Alt + Mouse movements - multiple rows checking

public class Main {
    public static void main(String[] args) {

        IUserRepository iUserRepository = new UserRepository();
        IDealerRepository iDealerRepository = new DealerRepositoryImpll();

        System.out.println();
        System.out.println("//______________Find All______________________//");
        System.out.println();

        for (Dealer dealer : iDealerRepository.findAll()) {
            System.out.println(dealer);
        }

        System.out.println();
        System.out.println("//______________Find one______________________//");
        System.out.println();

        try {
            System.out.println(iDealerRepository.findById(6L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

//        System.out.println();
//        System.out.println("//______________Save one______________________//");
//        System.out.println();
//
//        Dealer dealer = new Dealer();
//        dealer.setName("Audi Авилон");
//        dealer.setOpenDate(new Date(1_586_908_800_000L));
//        dealer.setLocationDescription("Москва, ул. Автозаводская, д. 23, корп. 5");
//        dealer.setLocationId(3L);
//        dealer.setCreated();
//        dealer.setChanged();
//        dealer.setOpenHour(9);
//
//        System.out.println(iDealerRepository.save(dealer));

        System.out.println();
        System.out.println("//____________________________________//");
        System.out.println();

        for (User user : iUserRepository.findAll()) {
            System.out.println(user);
        }

        System.out.println(iUserRepository
                .findAll()
                .stream()
                .map(User::getName)
                .collect(Collectors.joining(", ")));

    }
}
