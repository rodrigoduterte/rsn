export class UserProfileBean{

    constructor(
       public _username?: any,
       public _password?: any,
        public _firstName?: any,
        public _lastName?: any,
        public _email?: any,
        public _phoneNumber?: any,
      public _middleName?: any,
       public _occupation?: any,
        public _dob?: any,
         public _favoriteColor?: any,
      public _city?: any,
       public _relationshipStatus?: any,
        public _gender?: any,
         public _bio?: any,
          public _profilePic?: any,
      public _Posts?: any[],
       public _Likes?: any[] ) { }
  
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

   get username(): any{
      return this._username;
   }

   get email(): any{
      return this._email;
   }

   get phonenumber(): any{
      return this._phoneNumber;
   }


   get profilePic(): any{       
      return this._profilePic;
   }

   get firstName(): any{
      return this._firstName;
   }

   get lastName(): any{
      return this._lastName;
   }

   get middleName(): any{
      return this._middleName;
   }

   get occupation(): any{
      return this._occupation;
   }

   get dob(): any{
      return this._dob;
   }

   get favoriteColor(): any{
      return this._favoriteColor;
   }

   get city(): any{
      return this._city;
   }

   get relationshipStatus(): any{
      return this._relationshipStatus;
   }

   get gender(): any{
      return this._gender;
   }

   get bio():any{
      return this._bio;
   }







   set username(username:any){
   }

   set email(email:any){
   }

   set phonenumber(phoneNumber: any){
   }

   set profilePic(profilePic:any){       
   }

   set firstName(firstName: any){
   }

   set lastName(lastName:any){
   }

   set middleName(middleName:any){
   }

   set occupation(occupation:any){
   }

   set dob(dob:any){
   }

   set favoriteColor(favoriteColor:any){
   }

   set city(city:any){
   }

   set relationshipStatus(relationshipStatus :any){
   }



    }