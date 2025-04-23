package unlp.info.bd2.services.Impl;

import jakarta.transaction.*;
import org.springframework.data.domain.PageRequest;
import unlp.info.bd2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import unlp.info.bd2.model.*;
import unlp.info.bd2.services.ToursService;
import unlp.info.bd2.utils.ToursException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ToursServiceImpl implements ToursService {
    @Autowired
    private DriverUserRepository driverUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourGuideUserRepository tourGuideUserRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ToursService toursService;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private StopRepository stopRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ItemServiceRepository itemServiceRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public User createUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber) throws ToursException {
        User user = new User(username,password,fullName,email,birthdate,phoneNumber,true,null);
        return this.userRepository.save(user);
    }

    @Override
    public DriverUser createDriverUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String expedient) throws ToursException {
        DriverUser driverUser = new DriverUser(username,password,fullName,email,birthdate,phoneNumber,expedient,true,null);
        return this.driverUserRepository.save(driverUser);
    }

    @Override
    public TourGuideUser createTourGuideUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String education) throws ToursException {
        TourGuideUser tourGuideUser = new TourGuideUser(username,password,fullName,email,birthdate,phoneNumber,education,true,null);
        return this.tourGuideUserRepository.save(tourGuideUser);
    }

    @Override
    public Optional<User> getUserById(Long id) throws ToursException {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) throws ToursException {
        return Optional.empty();
    }

    @Override
    public User updateUser(User user) throws ToursException {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) throws ToursException {
        this.userRepository.delete(user);
    }

    @Override
    public Stop createStop(String name, String description) throws ToursException {
        Stop stop = new Stop(name, description);
        return this.stopRepository.save(stop);
    }

    @Override
    public List<Stop> getStopByNameStart(String name) {
        return this.stopRepository.findByNameStartingWith(name);
    }

    @Override
    public Route createRoute(String name, float price, float totalKm, int maxNumberOfUsers, List<Stop> stops) throws ToursException {
        Route route = new Route(name,price,totalKm,maxNumberOfUsers,stops);
        return this.routeRepository.save(route);
    }

    @Override
    public Optional<Route> getRouteById(Long id) {
        return this.routeRepository.findById(id);
    }

    @Override
    public List<Route> getRoutesBelowPrice(float price) {
        return this.routeRepository.findByPriceLessThan(price);
    }

    @Override
    public void assignDriverByUsername(String username, Long idRoute) throws ToursException {
        Optional<Route> route = this.routeRepository.findById(idRoute);
        Optional<DriverUser> driverUser = this.driverUserRepository.findDriverByUsername(username);
        if(route.isEmpty() || driverUser.isEmpty())throw new ToursException("No pudo realizarse la asignación");
        route.get().addDriver(driverUser.get());
        driverUser.get().addRoute(route.get());
        this.driverUserRepository.save(driverUser.get());
        this.routeRepository.save(route.get());
    }

    @Override
    public void assignTourGuideByUsername(String username, Long idRoute) throws ToursException {
        Optional<Route> route = this.routeRepository.findById(idRoute);
        Optional<TourGuideUser> tourGuideUser = tourGuideUserRepository.findByUsername(username);
        if(tourGuideUser.isEmpty() || route.isEmpty())throw new ToursException ("No pudo realizarse la asignación");
        route.get().addTourGuide(tourGuideUser.get());
        tourGuideUser.get().addRoute(route.get());
        this.tourGuideUserRepository.save(tourGuideUser.get());
        this.routeRepository.save(route.get());
    }

    @Override
    public Supplier createSupplier(String businessName, String authorizationNumber) throws ToursException {
        return null;
    }

    @Override
    public Service addServiceToSupplier(String name, float price, String description, Supplier supplier) throws ToursException {
        return null;
    }

    @Override
    public Service updateServicePriceById(Long id, float newPrice) throws ToursException {
        return null;
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return this.supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) throws ToursException {
        return Optional.empty();
    }

    @Override
    public Purchase createPurchase(String code, Route route, User user) throws ToursException {
        Purchase purchase = new Purchase(code,route,user);
        return this.purchaseRepository.save(purchase);
    }

    @Override
    public Purchase createPurchase(String code, Date date, Route route, User user) throws ToursException {
        Purchase purchase = new Purchase(code,date,route,user);
        return this.purchaseRepository.save(purchase);
    }

    @Override
    public ItemService addItemToPurchase(Service service, int quantity, Purchase purchase) throws ToursException {
        return null;
    }

    @Override
    public Optional<Purchase> getPurchaseByCode(String code) {
        return Optional.empty();
    }

    @Override
    public void deletePurchase(Purchase purchase) throws ToursException {
        this.purchaseRepository.delete(purchase);
    }

    @Override
    public Review addReviewToPurchase(int rating, String comment, Purchase purchase) throws ToursException {
        return null;
    }

    @Override
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        return List.of();
    }

    @Override
    public List<User> getUserSpendingMoreThan(float mount) {
        return List.of();
    }

    @Override
    public List<User> getUsersWithNumberOfPurchases(int number) {
        return List.of();
    }

    @Override
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        return List.of();
    }

    @Override
    public List<Supplier> getTopNSuppliersItemsSold(int n) {
        return List.of();
    }

    @Override
    public List<Purchase> getTop10MoreExpensivePurchasesWithServices() {
        return List.of();
    }

    @Override
    public List<User> getTop5UsersMorePurchases() {
        return List.of();
    }

    @Override
    public List<Route> getTop3RoutesWithMoreStops() {
        return this.routeRepository.findTop3RoutesByStopCount(PageRequest.of(0,3));
    }

    @Override
    public Long getCountOfPurchasesBetweenDates(Date start, Date end) {
        return purchaseRepository.countByDateBetween(start, end);
    }

    @Override
    public List<Route> getRoutesWithStop(Stop stop) {
        return this.routeRepository.findByStopsContaining(stop);
    }

    @Override
    public List<Purchase> getPurchaseWithService(Service service) {
        return List.of();
    }

    @Override
    public Long getMaxStopOfRoutes() {
        return this.routeRepository.findByMaxStopsCount();
    }

    @Override
    public Long getMaxServicesOfSupplier() {
        return 0L;
    }

    @Override
    public List<Route> getRoutsNotSell() {
        return List.of();
    }

    @Override
    public List<Route> getTop3RoutesWithMaxAverageRating() {
        List<Route>w =  this.routeRepository.findTop3RoutesByAverageRating(PageRequest.of(0,3));
        System.out.println(w.size());
        return w ;
    }

    @Override
    public List<Route> getRoutesWithMinRating() {
        return this.routeRepository.findRoutesByRatingEqualsOne();
    }

    @Override
    public Service getMostDemandedService() {
        return null;
    }

    @Override
    public Route getMostBestSellingRoute() {
        return null;
    }

    @Override
    public List<Service> getServiceNoAddedToPurchases() {
        return List.of();
    }

    @Override
    public List<TourGuideUser> getTourGuidesWithRating1() {
        return this.tourGuideUserRepository.findTourGuideUsersByRatingEqualsOne();
    }

    @Override
    public DriverUser getDriverUserWithMoreRoutes() {
        return this.driverUserRepository.findDriverUserByMaxRoutes();
    }
}
