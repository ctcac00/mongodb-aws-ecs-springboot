variable "execution_role_arn" {
  description = "The execution role ARN to use"
  type        = string
}

variable "container_image" {
  description = "Container image"
  type        = string
}

variable "aws_profile" {
  description = "AWS Profile"
  type        = string
  default     = "default"
}

variable "atlas_public_key" {
  description = "MongoDB Atlas Public Key"
  type        = string
}

variable "atlas_private_key" {
  description = "MongoDB Atlas Private Key"
  type        = string
}

variable "project_id" {
  description = "Atlas Existing Project ID Key"
  type        = string
}

variable "MONGODB_URI" {
  description = "MongoDB Atlas URI"
  type        = string
}
