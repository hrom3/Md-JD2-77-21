package by.bsuir;

import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;
import by.bsuir.repository.IDealerRepository;
import by.bsuir.repository.IUserRepository;
import by.bsuir.repository.impl.DealerRepositoryImpl;
import by.bsuir.repository.impl.UserRepositoryImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//Ctrl+W - word checking
//Alt+Shift - multiple cursor
//Alt + Mouse movements - multiple rows checking

public class Main {
    public static void main(String[] args) {

        IUserRepository iUserRepository = new UserRepositoryImpl();
        IDealerRepository iDealerRepository = new DealerRepositoryImpl();

        System.out.println();
        System.out.println("//______________Find All______________________//");
        System.out.println();

        for (Dealer dealer : iDealerRepository.findAll()) {
            System.out.println(dealer);
        }


        System.out.println();
        System.out.println("//______________Find by name ______________________//");
        System.out.println();

        for (Dealer dealer : iDealerRepository.searchDealersByQuery("aud")) {
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
//        dealer.setCloseHour(21);

//
//        System.out.println(iDealerRepository.save(dealer));

        System.out.println();
        System.out.println("//______________Save dealer______________________//");
        System.out.println();

        Dealer dealer = new Dealer();
        dealer.setName("Test");
        dealer.setOpenDate(new Date(1_586_000_000_000L));
        dealer.setLocationDescription("Москва");
        dealer.setLocationId(3L);
        dealer.setCreated();
        dealer.setChanged();
        dealer.setOpenHour(9);
        dealer.setCloseHour(21);

        dealer = iDealerRepository.save(dealer);

        System.out.println(dealer);

        System.out.println();
        System.out.println("//______________Update dealer______________________//");
        System.out.println();

        dealer.setName("Test_after_update");
        dealer.setOpenDate(new Date(8_586_000_000_000L));
        dealer.setLocationDescription(null);
        dealer = iDealerRepository.update(dealer);

        System.out.println(dealer);

        System.out.println();
        System.out.println("//______________Delete dealer______________________//");
        System.out.println();

        long id = dealer.getId();
        iDealerRepository.delete(dealer);

        System.out.println();
        System.out.println("//______________Search dealer for user______________________//");
        System.out.println();


        try {
            User user = iUserRepository.findById(10L);
            List<String> strDealers = new ArrayList<>(iDealerRepository.searchDealersForUser(user));

            for (String strDealer : strDealers) {
                System.out.println(strDealer);;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }








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
