export class PostBean{

    constructor(_profilePic?: any,
        _firstName?: string,
        _lastName?: string,
        _subtitle?: string,
        _bodyText?: string,
        _bodyImage?: string,
        _Likes?: object[],
        _Comments?: object[]){

            this._firstName = _firstName;
            this._lastName = _lastName;
            this._subtitle = _subtitle;
            this._bodyText = _bodyText;
            this._bodyImage = _bodyImage;
            this._Likes = _Likes;
            this._Comments = _Comments;

    }

    _profilePic: any;
    _firstName: string;
    _lastName: string;
    _subtitle: string;
    _bodyText: string;
    _bodyImage: string;
    _Likes: object[];
    _Comments: object[];
}