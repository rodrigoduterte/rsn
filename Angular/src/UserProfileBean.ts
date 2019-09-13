export class UserProfileBean{

    constructor(private _username?: string,
       private _password?: string,
        private _firstName?: string,
        private _lastName?: string,
        private _email?: string,
        private _phoneNumber?: string,
      private _middleName?: string,
       private _occupation?: string,
        private _dob?: string,
         private _favoriteColor?: string,
      private _city?: string,
       private _relationshipStatus?: string,
        private _gender?: string,
         private _bio?: string,
          private _profilePic?: any,
      private _Posts?: any[],
       private _Likes?: any[] ) { }
  
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


   //  getters causes errors when assigning Object to Session

   // get username(): string{
   //    return this._username;
   // }

   // get email(): string{
   //    return this._email;
   // }

   // get phonenumber(): string{
   //    return this._phoneNumber;
   // }


   // get profilePic(): any{       
   //    return this._profilePic;
   // }

   // get firstName(): string{
   //    return this._firstName;
   // }

   // get lastName(): string{
   //    return this._lastName;
   // }

   // get middleName(): string{
   //    return this._middleName;
   // }

   // get occupation(): string{
   //    return this._occupation;
   // }

   // get dob(): string{
   //    return this._dob;
   // }

   // get favoriteColor(): string{
   //    return this._favoriteColor;
   // }

   // get city(): string{
   //    return this._city;
   // }

   // get relationshipStatus(): string{
   //    return this._relationshipStatus;
   // }



    }