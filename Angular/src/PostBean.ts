export class PostBean{

    constructor(profilePic: any,
        firstName: string,
        lastName: string,
        subtitle: string,
        bodyText: string,
        bodyImage: string,
        Likes: object[],
        Comments: object[]){

            this.firstName = firstName;
            this.lastName = lastName;
            this.subtitle = subtitle;
            this.bodyText = bodyText;
            this.bodyImage = bodyImage;
            this.Likes = Likes;
            this.Comments = Comments;

    }

    profilePic: any;
    firstName: string;
    lastName: string;
    subtitle: string;
    bodyText: string;
    bodyImage: string;
    Likes: object[];
    Comments: object[];
}