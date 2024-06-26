export type CourseGetDto = {
  id: number;
  name: string;
  description: string;
  university: UniversityGetDto;
  professors: CourseProfessorsGetDto[];
  users: CourseUsersGetDto[];
};

export type ProfessorGetDto = {
  id: number;
  name: string;
  courses: ProfessorCoursesGetDto[];
};

export type UserGetDto = {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
  courses: UserCoursesGetDto[];
};

export type UniversityGetDto = {
  id: number;
  name: string;
  courses: UniversityCoursesGetDto[];
};

export type CourseProfessorsGetDto = {
  id: number;
  name: string;
};

export type CourseUsersGetDto = {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
  email: string;
};

export type ProfessorCoursesGetDto = {
  id: number;
  name: string;
  description: string;
  universityId: number;
  users: CourseUsersGetDto[];
};

export type UserCoursesGetDto = {
  id: number;
  name: string;
  description: string;
  universityId: number;
  professors: CourseProfessorsGetDto[];
};

export type UniversityCoursesGetDto = {
  id: number;
  name: string;
  description: string;
  professors: CourseProfessorsGetDto[];
  users: CourseUsersGetDto[];
};

export type UniversityDto = {
  name: string;
};

export type CourseDto = {
  name: string;
  description: string;
};

export type ProfessorDto = {
  name: string;
};

export type Quote = {
  id: number;
  quote: string;
  character: string;
  title: string;
  esrb: string;
  release: string;
};
