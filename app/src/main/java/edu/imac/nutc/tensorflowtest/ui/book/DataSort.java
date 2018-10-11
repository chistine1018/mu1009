package edu.imac.nutc.tensorflowtest.ui.book;

/**
 * Created by huanghuai on 2018/10/9.
 */

public class DataSort {

    //哺乳
    String Mammals = "ibex,hartebeest,impala,gazelle,Arabian camel,llama,weasel,mink,polecat,black-footed ferret,otter,skunk,badger,armadillo,three-toed sloth,orangutan,gorilla,chimpanzee,gibbon,siamang,guenon,patas,baboon,macaqu,langur,colobus,proboscis monkey,marmoset,capuchin, howler monkey , titi , spider monkey , squirrel monkey , Madagascar cat , indri , Indian elephant , African elephant , lesser panda , giant panda , bighorn , boxer , Great Dane , zebra , hamster , porcupine , wild boar , warthog , hippopotamus , ox , water buffalo , bison , fox squirrel , marmot , beaver , guinea pig , wood rabbit , hare , cheetah , brown bear , American black bear , ice bear , sloth bear , mongoose , meerkat , snow leopard , jaguar , lion , tiger , leopard , grey fox , cougar , African hunting dog , tabby , tiger cat , Persian cat , Siamese cat , Egyptian cat , timber wolf , white wolf , red wolf , coyote , dingo , dhole , hyena , red fox , kit fox , Arctic fox , toy poodle , miniature poodle , standard poodle , keeshond , Eskimo dog , dalmatian , malamute , Siberian husky , French bulldog , Irish wolfhound , miniature pinscher , Greater Swiss Mountain dog , Bernese mountain dog , German shepherd , Old English sheepdog , Shetland sheepdog , collie , malinois , Border collie , Border terrier , Irish water spaniel , Welsh springer spaniel , cocker spaniel , Sussex spaniel , Boston bull , Brittany spaniel , miniature schnauzer , golden retriever , Labrador retriever , Chesapeake Bay retriever , flat-coated retriever , curly-coated retriever , standard schnauzer , giant schnauzer , Scottish deerhound , Weimaraner , Italian greyhound , whippet , Ibizan hound , drake , king penguin , Blenheim spaniel , borzoi , English foxhound , Walker hound , bloodhound , black-and-tan coonhound , beagle , Afghan hound , toy terrier , Japanese spaniel , Pekinese , Maltese dog , sea lion , grey whale , killer whale , red-breasted merganser , goose , black swan , platypus , wallaby , wombat";
    public String[] mammalsArray = Mammals.toString().split(",");

    //鳥類
    String Bird = "cock,black grouse,albatross,bustard,ruddy turnstone,red-backed sandpiper,flamingo,little blueheron,Americanegret,spoonbill,toucan,bee eater,hornbill,hummingbird,peacock,quail,partridge,hen,ostrich,brambling, goldfinch, house finch, robin, bulbul, jay,magpie, chickadee, bald eagle, vulture,great grey owl";
    public String[] birdArray = Bird.toString().split(",");

    //爬蟲類
    String Reptile="bullfrog,leafhopper,cabbage butterfly,sulphur butterfly,lacewing,dragonfly,damselfly,weevil,tiger beetle,ladybug,ground beetle,long-horned beetle,leaf beetle,dung beetle,rhinoceros beetle,flatworm,nematode,centipede,macaw,sulphur-crested cockatoo,black and gold garden spider,barn spider,tarantula,garden spider,black widow,scorpion,trilobite,sidewinder, tree frog,vine snake,horned viper,boa constrictor,rock python,Indian cobra,green mamba,sea snake,night snake,thunder snake,hognose snake,green snake,king snake,garter snake,water snake,ringneck snake, tailed frog,Komodo dragon, loggerhead,African chameleon, banded gecko, common iguana, American chameleon, frilled lizard, alligator lizard,green lizard";

    public String[] reptileArray = Reptile.toString().split(",");


    //兩棲類
    String Amphibian="European fire salamander,Rhodesian ridgeback,oystercatcher,American lobster,spiny lobster,crayfish,hermit crab,chambered nautilus,Dungeness crab,rock crab,fiddler crab,sea slug,conch,snail,jellyfish,sea anemone,American alligator,African crocodile, common newt, spotted salamander, axolotl, leatherback turtle, mud turtle, terrapin, box turtle";
    public String[] amphibianArray = Amphibian.toString().split(",");


    //魚類
   public String Fish="barracouta,eel,coho,rock beauty,anemone fish,sturgeon,gar,lionfish,puffer,tench, goldfish,Angora, great white shark, tiger shark, stingray, eft";
    public String[] fishArray = Fish.toString().split(",");


    //其他類
    String Other = "ram,Bouvier des Flandres,hog,sorrel,lycaenid,starfish,sea urchin,sea cucumber,admiral,ringlet,monarch,fly,bee,ant,grasshopper,cricket,walking stick,cockroach,mantis,cicada,lynx,Mexican hairless,affenpinscher,Pembroke,Cardigan,Brabancon griffon,basenji,Leonberg,Newfoundland,Samoyed,Pomeranian,chow,Great Pyrenees,pug,Saint Bernard,bull mastiff,Tibetan mastiff,Appenzeller,EntleBucher,Doberman,Rottweiler,otterhound,briard,kelpie,komondor,clumber,groenendael,kuvasz,English springer,Kerry blue terrier,Airedale,German short-haired pointer,vizsla,English setter,Irish setter,Gordon setter,Scotch terrier,Lhasa,Tibetan terrier,silky terrier,soft-coated wheaten terrier,West Highland white terrier,cairn,Australian terrier,Dandie Dinmont,Irish terrier,Norfolk terrier,Norwich terrier,Lakeland terrier,Sealyham terrier,Yorkshire terrier,wire-haired fox terrier,isopod,Saluk,Staffordshire bullterrier,American Staffordshire terrier,Bedlington terrier,Norwegian elkhound,bluetick,Chihuahua,basset,redbone,papillon,Shih-Tzu,redshank,dowitcher,dugong,pelican,white stork,bittern,crane,limpkin,European gallinule,American coot,black stork,king crab,brain coral,koala,dummy,chiton,slug,echidna,ptarmigan,jacamar,tusker,lorikeet,coucal,African grey,ruffed grouse,prairie chicken,tick,harvestman,diamondback, triceratops,hammerhead, electric ray, junco, indigo bunting, water ouzel, kite, whiptail, agama, Gila monster";
    public String[] otherArray = Other.toString().split(",");


    public class Classification {



        //哺乳
        String[] Mammals = {"ibex","hartebeest","impala","gazelle","Arabian camel","llama","weasel","mink","polecat","black-footed ferret","otter","skunk","badger","armadillo","three-toed sloth","orangutan","gorilla","chimpanzee","gibbon","siamang","guenon","patas","baboon","macaque","langur","colobus","proboscis monkey","marmoset","capuchin","howler monkey","titi","spider monkey","squirrel monkey","Madagascar cat","indri","Indian elephant","African elephant","lesser panda","giant panda","bighorn","boxer","Great Dane","zebra","hamster","porcupine","wild boar","warthog","hippopotamus","ox","water buffalo","bison","fox squirrel","marmot","beaver","guinea pig","wood rabbit","hare","cheetah","brown bear","American black bear","ice bear","sloth bear","mongoose","meerkat","snow leopard","jaguar","lion","tiger","leopard","grey fox","cougar","African hunting dog","tabby","tiger cat","Persian cat","Siamese cat","Egyptian cat","timber wolf","white wolf","red wolf","coyote","dingo","dhole","hyena","red fox","kit fox","Arctic fox","toy poodle","miniature poodle","standard poodle","keeshond","Eskimo dog","dalmatian","malamute","Siberian husky","French bulldog","Irish wolfhound","miniature pinscher","Greater Swiss Mountain dog","Bernese mountain dog","German shepherd","Old English sheepdog","Shetland sheepdog","collie","malinois","Border collie","Border terrier","Irish water spaniel","Welsh springer spaniel","cocker spaniel","Sussex spaniel","Boston bull","Brittany spaniel","miniature schnauzer","golden retriever","Labrador retriever","Chesapeake Bay retriever","flat-coated retriever","curly-coated retriever","standard schnauzer","giant schnauzer","Scottish deerhound","Weimaraner","Italian greyhound","whippet","Ibizan hound","drake","king penguin","Blenheim spaniel","borzoi","English foxhound","Walker hound","bloodhound","black-and-tan coonhound","beagle","Afghan hound","toy terrier","Japanese spaniel","Pekinese","Maltese dog","sea lion","grey whale","killer whale","red-breasted merganser","goose","black swan","platypus","wallaby","wombat"};


        //鳥類
        String[] Bird = {,};


        //爬蟲類
        String[] Reptile = {};


        //兩棲類
        String[] Amphibian = {
        };


        //魚類
        String[] Fish = {};


        //其他類
        String[] Other = {,
        };



    }

}
