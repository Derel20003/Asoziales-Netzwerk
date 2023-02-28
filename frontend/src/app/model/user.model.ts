import {Post} from "./post.model";

export interface User {
  id: string,
  name: string,
  email: string,
  password: string,
  bio: string,
  favoriteCats: FavoriteCat[],
  subscriptions: Subscription[],
  subscribers: number,
  posts: Post[]
}

export interface FavoriteCat {
  name: string,
  imgURL: string
}

export interface Subscription {
  subscribedToId: string,
  timestamp: Date
}
