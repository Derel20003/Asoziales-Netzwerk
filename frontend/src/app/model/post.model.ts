export interface Interaction {
  type: string,
  timestamp: Date,
  interactorId: string
}

export interface Post {
  content: string,
  timestamp: Date,
  userId: string,
  interactions: Interaction[],
  comments: Post[]
}
