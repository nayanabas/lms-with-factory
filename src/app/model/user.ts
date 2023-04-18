import { Role } from "./role";

export class User {
   
    userName!: string;
    userPassword!: string;
    userFirstName!: string;
    userLastName!: string;
    role:Role|any;
}
