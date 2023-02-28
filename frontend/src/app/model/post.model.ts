export interface Interaction {
  type: string,
  timestamp: Date,
  interactorId: string
}

export interface Post {
  id: string,
  _id: string,
  content: string,
  timestamp: Date,
  userId: string,
  interactions: Interaction[],
  comments: Post[]
}

export enum InteractionType {
  DISLIKE = 'DISLIKE', HATE = 'HATE'
}
