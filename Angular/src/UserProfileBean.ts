export class UserProfileBean{

    constructor(private _username: string, private _password: string, private _firstName: string,private _lastName: string,
      private _middleName?: string, private _occupation?: string, private _DOB?: string, private _favoriteColor?: string,
      private _city?: string, private _relationshipStatus?: string, private _gender?: string, private _bio?: string, private _profilePic?: any,
      private _Posts?: object[], private _Likes?: object[] ) { }
  
    // userName: string;
    // password: string;
    // firstName: string;
    // middleName: string;
    // lastName: string;
    // occupation: string;
    // DOB :string;
    // favoriteColor: string; //use a color picker
    // city: string;
    // relationshipStatus: string;
    // gender: string;
    // bio: string;
    // profilePic: any;
    // Posts: object[];
    // Likes: object[];
    }